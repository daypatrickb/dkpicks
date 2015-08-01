json.array!(@picks) do |pick|
  json.extract! pick, :id, :game_id, :team_id, :diff, :result
  json.url pick_url(pick, format: :json)
end
