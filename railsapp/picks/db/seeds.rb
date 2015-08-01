# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)


Team.delete_all
ari = Team.create({ abbr: "ARI", name: "Arizona Cardinals"})
atl = Team.create({ abbr: "ATL", name: "Atlanta Falcons"})
bal = Team.create({ abbr: "BAL", name: "Baltimore Ravens"})
buf = Team.create({ abbr: "BUF", name: "Buffalo Bills"})
car = Team.create({ abbr: "CAR", name: "Carolina Panthers"})
chi = Team.create({ abbr: "CHI", name: "Chicago Bears"})
cin = Team.create({ abbr: "CIN", name: "Cincinnati Bengals"})
cle = Team.create({ abbr: "CLE", name: "Cleveland Browns"})
dal = Team.create({ abbr: "DAL", name: "Dallas Cowboys"})
den = Team.create({ abbr: "DEN", name: "Denver Broncos"})
det = Team.create({ abbr: "DET", name: "Detroit Lions"})
gb  = Team.create({ abbr: "GB",  name: "Green Bay Packers"})
hou = Team.create({ abbr: "HOU", name: "Houston Texans"})
ind = Team.create({ abbr: "IND", name: "Indianapolis Colts"})
jax = Team.create({ abbr: "JAX", name: "Jacksonville Jaguars"})
kc  = Team.create({ abbr: "KC",  name: "Kansas City Chiefs"})
mia = Team.create({ abbr: "MIA", name: "Miami Dolphins"})
min = Team.create({ abbr: "MIN", name: "Minnesota Vikings"})
ne  = Team.create({ abbr: "NE",  name: "New England Patriots"})
no  = Team.create({ abbr: "NO",  name: "New Orleans Saints"})
nyg = Team.create({ abbr: "NYG", name: "New York Giants"})
nyj = Team.create({ abbr: "NYJ", name: "New York Jets"})
oak = Team.create({ abbr: "OAK", name: "Oakland Raiders"})
phi = Team.create({ abbr: "PHI", name: "Philadelphia Eagles"})
pit = Team.create({ abbr: "PIT", name: "Pittsburgh Steelers"})
sd  = Team.create({ abbr: "SD",  name: "San Diego Chargers"})
sea = Team.create({ abbr: "SEA", name: "Seattle Seahawks"})
sf  = Team.create({ abbr: "SF",  name: "San Francisco 49ers"})
stl = Team.create({ abbr: "STL", name: "St. Louis Rams"})
tb  = Team.create({ abbr: "TB",  name: "Tampa Bay Buccaneers"})
ten = Team.create({ abbr: "TEN", name: "Tennessee Titans"})
was = Team.create({ abbr: "WAS", name: "Washington Redskins"})


GameTime.delete_all
thurssingle = GameTime.create({name: "THURSDAY_SINGLE"  , desc: "Thursday Night"  , style_class: "thurssingle" })
	
thanksgiving1 = GameTime.create({ name: "THANKSGIVING1" , desc: "Thursday 12:30 pm", style_class: "thanksgiving1"}) 
thanksgiving2 = GameTime.create({ name: "THANKSGIVING2" , desc: "Thursday 4:30 pm", style_class: "thanksgiving2"})
thanksgiving3 = GameTime.create({ name: "THANKSGIVING3" , desc: "Thursday 8:30 pm", style_class: "thanksgiving3"})

sunearly = GameTime.create({name: "EARLY_SUNDAY"  , desc: "Sunday 1:00pm" , style_class: "sunearly" })
sunlate = GameTime.create({name: "LATE_SUNDAY"  , desc: "Sunday 4:00pm" , style_class: "sunlate" })
sunnight = GameTime.create({name: "SUNDAY_NIGHT"  , desc: "Sunday 8:00pm" , style_class: "sunnight" })
monnight = GameTime.create({name: "MONDAY_NIGHT"  , desc: "Monday Night" , style_class: "monnight" })


playoff_sat1 = GameTime.create({ name: "PLAYOFF_SATURDAY1" , desc: "Saturday Game 1", style_class: "sunearly"})
playoff_sat2 = GameTime.create({ name: "PLAYOFF_SATURDAY2" , desc: "Saturday Game 2", style_class: "sunlate"})
playoff_sun1 = GameTime.create({ name: "PLAYOFF_SUNDAY1" , desc: "Sunday Game 1", style_class: "sunearly"})
playoff_sun2 = GameTime.create({ name: "PLAYOFF_SUNDAY2" , desc: "Sunday Game 2", style_class: "sunlate"})


sb_first = GameTime.create({ name: "SB_FIRST_HALF" , desc: "First Half", style_class: "sunearly"})
sb_second = GameTime.create({ name: "SB_SECOND_HALF" , desc: "Second Half", style_class: "sunlate"})

Game.delete_all
Game.create( { season:2015, week:1, home_team:ne,  away_team:pit , game_time: thurssingle}  )
Game.create( { season:2015, week:1, home_team:chi, away_team:gb , game_time: sunearly}  )
Game.create( { season:2015, week:1, home_team:hou, away_team:kc , game_time: sunearly}  )
Game.create( { season:2015, week:1, home_team:nyj, away_team:cle , game_time: sunearly}  )
Game.create( { season:2015, week:1, home_team:buf, away_team:ind , game_time: sunearly}  )
Game.create( { season:2015, week:1, home_team:was, away_team:mia , game_time: sunearly}  )
Game.create( { season:2015, week:1, home_team:jax, away_team:car , game_time: sunearly}  )
Game.create( { season:2015, week:1, home_team:stl, away_team:sea , game_time: sunearly}  )
Game.create( { season:2015, week:1, home_team:ari, away_team:no , game_time: sunlate}  )
Game.create( { season:2015, week:1, home_team:sd,  away_team:det , game_time: sunlate}  )
Game.create( { season:2015, week:1, home_team:tb,  away_team:ten , game_time: sunlate}  )
Game.create( { season:2015, week:1, home_team:oak, away_team:cin , game_time: sunlate}  )
Game.create( { season:2015, week:1, home_team:den, away_team:bal , game_time: sunlate}  )
Game.create( { season:2015, week:1, home_team:dal, away_team:nyg, game_time: sunnight }  )
Game.create( { season:2015, week:1, home_team:atl, away_team:phi , game_time: monnight}  )
Game.create( { season:2015, week:1, home_team:sf,  away_team:min , game_time: monnight}  )


Player.delete_all
Player.create( { name: "Dave", email: "", google_id: "", team: nyg  } )
Player.create( { name: "Dennis", email: "", google_id: "" , team: cin  } )
Player.create( { name: "Dylan", email: "", google_id: "", team: ne  } )
Player.create( { name: "Jeff", email: "", google_id: "", team: ne } )
Player.create( { name: "Justin", email: "", google_id: "", team: min  } )
Player.create( { name: "Pat", email: "", google_id: "", team: ne  } )


Pick.delete_all







