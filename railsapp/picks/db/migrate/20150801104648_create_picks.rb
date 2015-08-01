class CreatePicks < ActiveRecord::Migration
  def change
    create_table :picks do |t|
      t.integer :game_id
      t.integer :player_id
      t.integer :team_id
      t.integer :diff
      t.integer :result

      t.timestamps
    end
  end
end
