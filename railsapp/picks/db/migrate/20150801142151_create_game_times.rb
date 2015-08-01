class CreateGameTimes < ActiveRecord::Migration
  def change
    create_table :game_times do |t|
      t.text :name
      t.text :desc
      t.text :style_class

      t.timestamps
    end
  end
end
