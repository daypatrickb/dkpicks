module PlayersHelper


  def player_style(player)

  	return "patriots-alt" if player.name == "Dylan"

  	team_name = player.team.name.split

  	return team_name[team_name.count - 1].downcase


  end

end
