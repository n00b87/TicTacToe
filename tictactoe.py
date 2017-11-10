ttt_board = [ ["" for x in range(3)] for y in range(3)]

def init():
	ttt_board = [ ["" for x in range(3)] for y in range(3)]
	
def drawBoard():
	for y in range(3):
		for x in range(3):
			if ttt_board[y][x] == "":
				print "<" + str(y*3+x+1) + ">",
			else:
				print " " + ttt_board[y][x] + " ",
			
			if x < 2:
				print " | ",
			else:
				print ""
		if y < 2:
			print "---------------------"

def selectMove(player, square):
	ttt_value = "X"
	if player == 2:
		ttt_value = "O"
	
	if square <= 0 or square > 9:
		return False
	
	y = (square-1)/3
	x = (square-1)%3
	
	if ttt_board[y][x] == "":
		ttt_board[y][x] = ttt_value
	else:
		return False
	
	return True

def playerHasWon(player):
	player_char = "X"
	if player == 2:
		player_char = "O"
	match = [0 for x in range(8)]
	
	for y in range(3):
		if ttt_board[y][0] == player_char:
			match[0] += 1
		if ttt_board[y][1] == player_char:
			match[1] += 1
		if ttt_board[y][2] == player_char:
			match[2] += 1
		
	for x in range(3):
		if ttt_board[0][x] == player_char:
			match[3] += 1
		if ttt_board[1][x] == player_char:
			match[4] += 1
		if ttt_board[2][x] == player_char:
			match[5] += 1
			
	if ttt_board[0][0] == player_char and ttt_board[1][1] == player_char and ttt_board[2][2] == player_char:
		match[6] = 3
					
	if ttt_board[0][2] == player_char and ttt_board[1][1] == player_char and ttt_board[2][0] == player_char:
		match[7] = 3
		
	for i in range(8):
		if match[i] == 3:
			return True
	return False


game_continue = True
game_isRunning = True
current_player = 1
square = 0
play_again = " "

while game_continue:
	init()
	current_player = 1
	
	while game_isRunning:
		drawBoard()
		square = eval( raw_input("\nPlayer " + str(current_player) + " select a square:"))
		
		while not selectMove(current_player, square):
			print "\nYOU CANT MAKE THAT MOVE!!!\n\n"
			drawBoard()
			square = eval( raw_input( "Player " + str(current_player) + ", Please Select an Empty Square: " ) )	
			print "\n"
			
		if playerHasWon(current_player):
			print "\nPLAYER " + str(current_player) + " WINS!!!"
			break
				
		if	current_player == 1:
			current_player = 2
		else:
			current_player = 1
				
	play_again = raw_input("\nDo you want to play again? (y/n): ")
			
	if play_again[0] == 'Y' or play_again[0] =='y':
		continue
	else:
		break
			
		
