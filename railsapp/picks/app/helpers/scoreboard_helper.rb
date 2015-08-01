module ScoreboardHelper

  def name_for_week(week)
  	return "Week #{week}" if week <= 17
  	return "Wild Card" if week == 18
  	return "Divisional" if week == 19
  	return "Championship" if week == 20
  	return "Super Bowl" if week == 21
  end



end
