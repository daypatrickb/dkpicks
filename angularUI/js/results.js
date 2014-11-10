var app = angular.module("Picks", []);

app.controller("ResultsControl", function($scope, $http) {

	$http.get("./data/week10DummyResults.json").
    success(function(data, status, headers, config) {
      $scope.results = data;

      console.log($scope.results);
    }).
    error(function(data, status, headers, config) {
      // log error
    });
});