let app = new Vue({
	el:"#app",
	data:{
		status: "Not Logged",
        logged: false,
        user: {}
	}
})

let gamesData
let inits = 0;

const loadData = () => {
	fetch('/api/games')
	.then(response => response.json())
	.then(json => {
		inits++;
		gamesData = json.games
		app.user = json.player
		if(inits<=1){
			changeDateFormat()
			gamesTable()
			leaderTable(leaderboard(), document.getElementById('leaderboard'))
		}
		if(app.user != "guest"){
            app.status = "Logged"
            app.logged = true
        }
	})
	.catch(error => console.log(error))
}

loadData()


$('#login-form').submit(function () {
    $.post("/api/login", {
        username: $("#login-username").val(),
        password: $("#login-pwd").val()
    })
        .done(function () {
            console.log("Logged in")
            
            app.logged = true
            app.status = "Logged"

			loadData()
        })
        .fail(function () {
            alert("Incorrect email or password.")
        })
})

$("#signup-form").submit(function(){
    $.post('/api/players',{
        username: $("#signup-username").val(),
        name: $("#signup-name").val(),
        password: $("#signup-pwd").val()
    })
        .done(function(){
            alert("Registered")
        })
        .fail(function(){
            alert("Email Already Registered")
        })
})

$('#logout-form').submit(function () {
    $.post('/api/logout')
        .done(function () {
            console.log("Logged out")
            app.logged = false
            app.status = "Not Logged"
            $("#login-email").val("")
            $("#login-pwd").val("")
			loadData()
        })
        .fail(function () {
            console.log("Logout error")
        })
})

const changeDateFormat = () => {
    for (var i in gamesData){
        var newDate = new Date(gamesData[i].created).toLocaleString();
        gamesData[i].created = newDate
    }
}

const gamesTable = () => {
	let table = document.getElementById('games-table')
	let head = document.createElement('THEAD')
	let row = document.createElement('TR')
	let cell1 = document.createElement('TH')
	cell1.innerText = '#'
	row.appendChild(cell1)
	let cell2 = document.createElement('TH')
	cell2.innerText = 'created'
	row.appendChild(cell2)
	let cell3 = document.createElement('TH')
	cell3.innerText = 'players'
	cell3.colSpan = 2
	row.appendChild(cell3)
	head.appendChild(row)

	table.appendChild(head)


	let body = document.createElement('TBODY')
	let index=0;
	let link = document.createElement('a')
	link.setAttribute("href","")
	gamesData.forEach(game => {
		let row = document.createElement('TR')
		let auxrow = document.createElement('TR')
		for(item in game){
			if(typeof game[item] == 'object'){
				if(game[item].length == 1){
					let cell = document.createElement('TD')
					let cell2 = document.createElement('TD')
					cell.innerText = game[item][0].player.username
					cell2.innerText = 'waiting...'
					link.appendChild(cell)
					link.appendChild(cell2)
					row.appendChild(link)
				} else{
					game[item].forEach(gamePlayer => {
						let cell = document.createElement('TD')
						cell.innerText = gamePlayer.player.username
						row.appendChild(cell)
					})
				}
				
			} else{
				if(game[item].length==1){
					let cell = document.createElement('TD')
					cell.innerText = game[item]
					link.appendChild(cell)
				}else{
					let cell = document.createElement('TD')
					cell.innerText = game[item]
					row.appendChild(cell)
				}
			}
			
		}

		body.appendChild(row)
		/*if(game.gamePlayers.length==2){
			body.appendChild(row)
		}else if(game.gamePlayers.length==1){
			let link = document.createElement("a")
			link.setAttribute("href","")
			link.appendChild(row)
			auxrow.appendChild(row)
			body.appendChild(auxrow)
			//body.childNodes[index].appendChild()
		}*/
		index++

	})

	table.appendChild(body)
}

const leaderboard = () => {
	let leaderboard = []
	let aux = []

	gamesData.forEach(game => {
		game.gamePlayers.forEach(gamePlayer => {
			if(aux.indexOf(gamePlayer.player.id) == -1){
				aux.push(gamePlayer.player.id)
				let obj = {}
				obj.id = gamePlayer.player.id
				obj.username = gamePlayer.player.username
				obj.score = gamePlayer.score
				obj.won = gamePlayer.score == 3 ? 1 : 0
				obj.lost = gamePlayer.score == 0 ? 1 : 0
				obj.tied = gamePlayer.score == 1 ? 1 : 0
				leaderboard.push(obj)
			} else{
				leaderboard.forEach(player => {
					if(player.id == gamePlayer.player.id){
						player.score += gamePlayer.score
						player.won += gamePlayer.score == 3 ? 1 : 0
						player.lost += gamePlayer.score == 0 ? 1 : 0
						player.tied += gamePlayer.score == 1 ? 1 : 0
					}
				})
			}
		})
	})
	return leaderboard
}

const leaderTable = (leaders, table) => {
	let head = document.createElement('THEAD')
	let row = document.createElement('TR')
	for (key in leaders[0]){
		let cell = document.createElement('TH')
		cell.innerText = key
		row.appendChild(cell)
	}
	head.appendChild(row)
	
    let body = document.createElement('TBODY')
    leaders.forEach(leader => {
		let row = document.createElement('TR')
		for(item in leader){
			let cell = document.createElement('TD')
			cell.innerText = leader[item]
			row.appendChild(cell)
		}
		body.appendChild(row)
	})

	table.appendChild(head)
	table.appendChild(body)
}
