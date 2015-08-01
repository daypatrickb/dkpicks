json.array!(@players) do |player|
  json.extract! player, :id, :name, :email, :google_id
  json.url player_url(player, format: :json)
end
