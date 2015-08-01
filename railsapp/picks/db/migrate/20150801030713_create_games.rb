class CreateGames < ActiveRecord::Migration
  def change
    create_table :games do |t|
      t.integer :season
      t.integer :week
      t.integer :home_team_id
      t.integer :away_team_id
      t.integer :home_score
      t.integer :away_score
      t.integer :game_time_id

      t.timestamps
    end
  end
end
