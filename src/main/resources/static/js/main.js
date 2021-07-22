function GerarMatricula(){
	var txt = "ACA";
	var aleatorio = Math.floor(Math.random()*10000);
	document.getElementById('matricula').value = (txt + aleatorio);
}