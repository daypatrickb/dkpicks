var app = angular.module("Picks", []);

app.controller("PostsCtrl", function($scope, $http) {

  $scope.gameFinal = function(game) {
  	return game.q == "F" || game.q == "FO";
  };
	$scope.gameStarted = function(game) {
		return $scope.gameFinal(game) || !$scope.gameScheduled(game) || $scope.gameHalftime(game);
	};
	$scope.gameScheduled = function(game) {
		return game.q == "P";
	};
	$scope.gameHalftime = function(game) {
		return game.q == "H";
	};

	$scope.homeWon = function(game) {
		return game.hs > game.vs;
	};

	$scope.prettyPrintQuarter = function(game) {
		switch(game.q) {
			case "1":
				return "Q1";
			case "2":
				return "Q2";
			case "H":
				return "Half";
			case "3":
				return "Q3";
			case "4":
				return "Q4";
			case "OT":
				return "OT";
			case "F":
				return "Final";
			default:
				return "?";
		}
	};

	$scope.makePick = function(gameIndex, team) {
		$scope.posts.gms[gameIndex].pick = team;
		var primBtn;
		var defaultBtn;
		if (team == $scope.posts.gms[gameIndex].v) {
			primBtn = '#' + gameIndex + '-awayBtn'
			defaultBtn = '#' + gameIndex + '-homeBtn'
		}
		else {
			primBtn = '#' + gameIndex + '-homeBtn'
			defaultBtn = '#' + gameIndex + '-awayBtn'
		}
		$(primBtn).removeClass('btn-default').addClass('btn-primary');;
		$(defaultBtn).removeClass('btn-primary').addClass('btn-default');

	};

	$http.get("./data/ss.json").
    success(function(data, status, headers, config) {
      $scope.posts = data;
      //Add the "pickDiff" field to each game
      angular.forEach($scope.posts.gms, function(value, index) {
      	value.pickDiff = 0;
      	value.pick = "";
      });

		  $scope.isRegularSeason = function() {
		  	return $scope.posts.t == "REG";
		  }

      console.log($scope.posts);
    }).
    error(function(data, status, headers, config) {
      // log error
    });
});