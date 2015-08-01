class PicksController < ApplicationController
  before_action :set_pick, only: [:show, :edit, :update, :destroy]

  # GET /picks
  # GET /picks.json
  def index

    if params[:season]
      @season = params[:season].to_i
    else
      @season = 2015
    end

    if params[:week]
      @week = params[:week].to_i
    else
      @week = 1
    end

    @players = Player.all

    @games = Game.where({season:@season, week:@week})

    @gametimes = @games.map{ |game| game.game_time }.uniq


    @sidebar_content = []
    rowsadded = 0

    if @week <= 17 
      @gametimes.each do |gametime|
        @sidebar_content.push({ styleclass: gametime.style_class , content: gametime.desc })
        rowsadded += 1
      end

      @sidebar_content.push({ styleclass: "spacer-black" , content: "." })

    end

    
    @sidebar_content.push({ styleclass: "correct" , content: "1 Point" })
    @sidebar_content.push({ styleclass: "closest" , content: "2 Points" })
    @sidebar_content.push({ styleclass: "exact" , content: "3 Points" })

    for i in 0...((@games.count + 3) - (rowsadded + 1 + 3))
      @sidebar_content.push({ styleclass: "spacer-black" , content: "." })
    end



  end

  # GET /picks/1
  # GET /picks/1.json
  def show
  end

  # GET /picks/new
  def new
    @pick = Pick.new
  end

  # GET /picks/1/edit
  def edit
  end

  # POST /picks
  # POST /picks.json
  def create
    @pick = Pick.new(pick_params)

    respond_to do |format|
      if @pick.save
        format.html { redirect_to @pick, notice: 'Pick was successfully created.' }
        format.json { render :show, status: :created, location: @pick }
      else
        format.html { render :new }
        format.json { render json: @pick.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /picks/1
  # PATCH/PUT /picks/1.json
  def update
    respond_to do |format|
      if @pick.update(pick_params)
        format.html { redirect_to @pick, notice: 'Pick was successfully updated.' }
        format.json { render :show, status: :ok, location: @pick }
      else
        format.html { render :edit }
        format.json { render json: @pick.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /picks/1
  # DELETE /picks/1.json
  def destroy
    @pick.destroy
    respond_to do |format|
      format.html { redirect_to picks_url, notice: 'Pick was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_pick
      @pick = Pick.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def pick_params
      params.require(:pick).permit(:game_id, :team_id, :diff, :result)
    end
end
