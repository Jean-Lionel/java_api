const baseURL = "http://localhost:8080/locationAppartement/webapi/"; 
jQuery(document).ready(function() {
	let villes = []
	jQuery.get(baseURL+"villes", {}, function(data, textStatus, xhr) {
	  //optional stuff to do after success
	  let options = ""
		data.forEach( function(element, index) {
			// statements
			options += `<option value="${element.id}"> ${element.name} </option>`
		});
		//console.log(options)
		$("#ville_id").html(options)
	});
	$("#search").on("click",function(e){
		e.preventDefault()
		let semaine = $("#semaine").val()
		let ville = $("#ville_id").val()
		console.log(semaine,ville)
		let url = baseURL+'journals/'+semaine+'/'+ville+'/'
		console.log(url)
		jQuery.get(url, function(data, textStatus, xhr) {
		  //optional stuff to do after success
		  $("#container_ville").html(makeTableInfo(data))
		});
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
					</tr>
	 			`
	 		trs += min_tr
	})

	table_info += trs + `</tbody></table>`

	return table_info
}

