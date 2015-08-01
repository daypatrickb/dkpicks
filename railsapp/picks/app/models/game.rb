class Game < ActiveRecord::Base

	belongs_to :home_team, :class_name => "Team"
	belongs_to :away_team, :class_name => "Team"

	belongs_to :game_time


	def winner
		return home_team if home_score > away_score
		return away_team if away_score > home_score
		return nil
	end

	def differential
		return (home_score - away_score).abs
	end

	def result
		winning_team = winner

		return "#{winning_team.abbr} #{differential}" if winning_team

		return "TIE"
	end



end
