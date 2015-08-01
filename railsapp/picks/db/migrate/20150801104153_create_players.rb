class CreatePlayers < ActiveRecord::Migration
  def change
    create_table :players do |t|
      t.text :name
      t.text :email
      t.text :google_id
      t.integer :team_id

      t.timestamps
    end
  end
end
