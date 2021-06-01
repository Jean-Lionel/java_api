const baseURL = "http://localhost:8080/locationAppartement/webapi/"; 


jQuery(document).ready(function() {
	jQuery.get(baseURL +"appartements/", {}, function(data, textStatus, xhr) {
	  //optional stuff to do after success
	  //console.log(data)
	  let data_info = makeTableInfo(data)

	  $("#appartement").html(data_info)
	});
	
	
});

function makeTableInfo(data){
	let table_info = `
	<table>
		<thead>
			<tr>
				<th># </th>
				<th>Ville </th>
				<th>Appartement </th>
			</tr>
		</thead>
		<tbody>
	`
	let trs = "";

	data.forEach( function(element, index) {
		let min_tr = `<tr>
					<td> ${index} </td> 
					<td>${element.ville.name}</td>
					<td>${element.name}</td>
					<td><button onclick="showDescription(${element.id},'${element.name}','${element.ville.name}')">Description</button> </td>
					</tr>
	 			`
	 		trs += min_tr
	})

	table_info += trs + `</tbody></table>`

	return table_info
}



function showDescription(id,appartement_name,ville_name){
	let response = {}
	const myKey = "cc6e9807517e044600d954e7d557699b"
	const url = `https://api.openweathermap.org/data/2.5/weather?q=${ville_name}&appid=cc6e9807517e044600d954e7d557699b`

	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
	})
	.done(function(data) {
		dataForDescription(data,ville_name,appartement_name)
		console.log("success");
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
	});
}

function dataForDescription(data,ville_name,appartement_name){

// 	{
//     "coord": {
//         "lon": 2.3488,
//         "lat": 48.8534
//     },
//     "weather": [
//         {
//             "id": 803,
//             "main": "Clouds",
//             "description": "broken clouds",
//             "icon": "04d"
//         }
//     ],
//     "base": "stations",
//     "main": {
//         "temp": 283.82,
//         "feels_like": 283.19,
//         "temp_min": 282.9,
//         "temp_max": 285.25,
//         "pressure": 1007,
//         "humidity": 86
//     },
//     "visibility": 10000,
//     "wind": {
//         "speed": 0.89,
//         "deg": 325,
//         "gust": 4.02
//     },
//     "clouds": {
//         "all": 75
//     },
//     "dt": 1621844480,
//     "sys": {
//         "type": 2,
//         "id": 2012208,
//         "country": "FR",
//         "sunrise": 1621828709,
//         "sunset": 1621885005
//     },
//     "timezone": 7200,
//     "id": 2988507,
//     "name": "Paris",
//     "cod": 200
// }


	$("#temperature").html(`
		<h2>APPARTEMENT: ${appartement_name}</h2>
		<h1>VILLE : ${ville_name} TEMPERATURE : ${data.main.temp} °F  </h1> 
		<em>TEMPERATURE MINIMUM : ${data.main.temp_min} °F
		 ET TEMPERATURE temp_max : ${data.main.temp_min} °F  </em> 
		<p>CORDONNE GEOGRAPHIQUE : LONG:  ${data.coord.lon} , LAT :  ${data.coord.lat}</p> 
		<h1>PAYS : ${data.sys.country} </h1> 
		<h1>humidité : ${data.main.humidity} %</h1> 
		<h1>weather Description : ${data.weather} °F</h1> 
		<h1>TEMPERATURE : ${data.main.temp} °F</h1> 

		`)
	console.log(data.main.temp,' 0 F')
	//$("#description").html("Je suis en bonne sante")



}