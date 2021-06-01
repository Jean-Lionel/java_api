const baseURL = "http://localhost:8080/locationAppartement/webapi/";

let villesList = []

const getData = (url, header) =>{
	$.ajax({
			url: baseURL+url,
			type: 'GET',
		    contentType: 'application/json; charset=utf-8'
		})
		.done(function(e) {
			//console.log(e);
			villesList = e
			let data = makeDataTable(e, header)

			$("#container_ville").html(data)
		})
		.fail(function(e) {
			console.log("ERROR");
		})
		.always(function() {
			console.log("COMPLETE");
		});
}
$("#saveVille").on('click', function(event) {
	event.preventDefault();
	/* Act on the event */
	let name = $("#name_ville").val()
	//console.log(ville)
	if(name.length < 3){
		alert("ENTRE LE NOM VALIDE")
		return;
	}

	$("#name_ville").val("")
	const ville = {
		name : name
	}

	$.ajax({
			url: baseURL+'villes',
			type: 'POST',
			contentType: 'application/json; charset=utf-8',
			accept: 'application/json',
		    data : JSON.stringify(ville),
		})
		.done(function(e) {
			loadVille()
			console.log(e);
			// let data = makeDataTable(e, ["name"])
			// $("#content").html(data)
		})
		.fail(function(e) {
			loadVille()
			console.log("ERROR");
		})
		.always(function() {
			console.log("COMPLETE");
		});
});


function  makeDataTable(data, title){
	let headers = "<tr><th> #</th>"

	for(let i=0; i< title.length; i++){
		headers += `<th> 
					${title[i]}
					</th>`
	}
	headers += "</tr>"

	let tbody = ""

	for(let i=0; i< data.length; i++){
		
		tbody += `<tr> <td>${i+1}</td>`
		for(let j = 0; j<title.length ; j++){
			//console.log(title[j])
			
			tbody += `<td> 
					${data[i][title[j]] ? data[i][title[j]] : "" }
					</td>
					
					`
		}
		tbody += ` </tr>`
	}

	let table = `

				<table>
					<thead>
					${headers}
					</thead>
					<tbody>
					${tbody}
					</tbody>
				</table>`

	return table;
}

function modifierElement(e)
{
	let el = villesList.filter(function(element) {
		return element.id == e;
	});
	console.log(el[0])
}


const a = document.getElementById("valider_btn")
a.addEventListener("click", function(e){
	e.preventDefault()
	const semaine = $("#semaine").val()
	const semaine_url =baseURL +"journals/"+semaine+"/"
	//console.log(semaine_url)
	$.ajax({
		url: semaine_url,
		type: 'GET',
		dataType: 'json',
	})
	.done(function(data) {
		//console.log(data)
		let val = makeDataTable(data, ['name'])
		 $("#container_ville").html(val)
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});
})

