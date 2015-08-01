class ScoreboardController < ApplicationController
  def index

    if params[:season]
      @season = params[:season].to_i
    else
      @season = 2015
    end

    @players = Player.all

  end


  def regenerate
	Pick.delete_all

	Game.delete_all

	thurssingle = GameTime.find_by({ name: "THURSDAY_SINGLE"  })
	thanksgiving1 = GameTime.find_by({ name: "THANKSGIVING1" }) 
	thanksgiving2 = GameTime.find_by({ name: "THANKSGIVING2" })
	thanksgiving3 = GameTime.find_by({ name: "THANKSGIVING3" })

	sunearly = GameTime.find_by({name: "EARLY_SUNDAY"  })
	sunlate = GameTime.find_by({name: "LATE_SUNDAY"  })
	sunnight = GameTime.find_by({name: "SUNDAY_NIGHT"  })
	monnight = GameTime.find_by({name: "MONDAY_NIGHT" })

	playoff_sat1 = GameTime.find_by({ name: "PLAYOFF_SATURDAY1" })
	playoff_sat2 = GameTime.find_by({ name: "PLAYOFF_SATURDAY2" })
	playoff_sun1 = GameTime.find_by({ name: "PLAYOFF_SUNDAY1" })
	playoff_sun2 = GameTime.find_by({ name: "PLAYOFF_SUNDAY2" })

	sb_first = GameTime.find_by({ name: "SB_FIRST_HALF" })
	sb_second = GameTime.find_by({ name: "SB_SECOND_HALF" })

	for week in 1..21

		num_games = 16

		num_games = 4 if week == 18 || week == 19
		num_games = 2 if week == 20 || week == 21

		all_teams = Team.all.shuffle

		for i in 0...num_games

			game = Game.new
			game.season = 2015
			game.week = week

			game.home_team = all_teams.pop
			game.away_team = all_teams.pop

			game.home_score = rand(43)
			game.away_score = rand(43)


			gametime = sunearly

			if (week == 13)
				## thanksgiving!!!!
				if (i == 0) 
					gametime = thanksgiving1
				elsif (i == 1) 
					gametime = thanksgiving2
				elsif (i == 2) 
					gametime = thanksgiving3
				elsif (i <= 9) 
					gametime = sunearly
				elsif (i <= 13) 
					gametime = sunlate
				elsif (i == 14) 
					gametime = sunnight
				elsif (i == 15) 
					gametime = monnight
				end

			elsif (week <= 17)
			
				if (i == 0) 
					gametime = thurssingle
				elsif (i <= 9) 
					gametime = sunearly
				elsif (i <= 13) 
					gametime = sunlate
				elsif (i == 14) 
					gametime = sunnight
				elsif (i == 15) 
					gametime = monnight
				end

			elsif (week == 18 || week == 19)
			
				if (i == 0) 
					gametime = playoff_sat1
				elsif (i == 1) 
					gametime = playoff_sat2
				elsif (i == 2) 
					gametime = playoff_sun1
				elsif (i == 3) 
					gametime = playoff_sun2
				end
			
			elsif (week == 20) 
			
				if (i == 0) 
					gametime = playoff_sun1
				elsif (i == 1) 
					gametime = playoff_sun2
				end
			
			elsif (week == 21)
			
				if (i == 0) 
					gametime = sb_first
				elsif (i == 1) 
					gametime = sb_second
				end
			
			end


			game.game_time = gametime

			game.save
		end



		
	end

	players = Player.all
	games = Game.all

	players.each do |player|
		games.each do |game|

			picked_team = [game.home_team , game.away_team].sample

			Pick.create( { player: player , game: game, team: picked_team, diff: rand(30)  } )
		end
	end

	redirect_to :back

  end

end
