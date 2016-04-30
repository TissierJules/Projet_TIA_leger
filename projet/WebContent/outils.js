/**
 * 
 */
var flag = 0;
var x1 = 0;
var y1 = 0;

function bleu(){flag = 1;}

function beige(){flag = 2;}

function marron(){flag = 3;}

function orange(){flag = 4;}

function rouge(){flag = 5;}

function pnj(){flag = 6;}

function fsn(){flag = 11;}

function fsone(){flag = 12;}

function foe(){flag = 13;}

function fnose(){flag = 14;}

function fns(){flag = 15;}

function fneso(){flag = 16;}

function feo(){flag = 17;}

function fseno(){flag = 18;}

function mask(){flag = 20;}

function mask2(){flag = 21;}

function rm(){flag = 22;}

function mv(){flag = 23;}

function Marqueur(event,id){
	var x = event.pageX - 10;
	var y = event.pageY - 10;
	var fichier = encodeURIComponent("carte.xml");
	
	if (flag < 20){
		if (flag == 1){
			var source = encodeURIComponent("bleu.png");
			var type = encodeURIComponent("token");
		}
		if (flag == 2){
			var source = encodeURIComponent("beige.png");
			var type = encodeURIComponent("token");
		}
		if (flag == 3){
			var source = encodeURIComponent("marron.png");
			var type = encodeURIComponent("token");
		}
		if (flag == 4){
			var source = encodeURIComponent("orange.png");
			var type = encodeURIComponent("token");
		}
		if (flag == 5){
			var source = encodeURIComponent("rouge.png");
			var type = encodeURIComponent("token");
		}
		if (flag == 6){
			var source = encodeURIComponent("pnj.png");
			var type = encodeURIComponent("token");
		}
		if (flag == 11){
			var source = encodeURIComponent("fsn.png");
			var type = encodeURIComponent("fleche");
		}
		if (flag == 12){
			var source = encodeURIComponent("fsone.png");
			var type = encodeURIComponent("fleche");
		}
		if (flag == 13){
			var source = encodeURIComponent("foe.png");
			var type = encodeURIComponent("fleche");
		}
		if (flag == 14){
			var source = encodeURIComponent("fnose.png");
			var type = encodeURIComponent("fleche");
		}
		if (flag == 15){
			var source = encodeURIComponent("fns.png");
			var type = encodeURIComponent("fleche");
		}
		if (flag == 16){
			var source = encodeURIComponent("fneso.png");
			var type = encodeURIComponent("fleche");
		}
		if (flag == 17){
			var source = encodeURIComponent("feo.png");
			var type = encodeURIComponent("fleche");
		}
		if (flag == 18){
			var source = encodeURIComponent("fseno.png");
			var type = encodeURIComponent("fleche");
		}

		var xhr = new XMLHttpRequest();
		if (type != null){
			xhr.open("GET", "Outils?x="+x+"&y="+y+"&type="+type+"&fichier="+fichier+"&source="+source, true);
			xhr.send(null);
		}
		flag = 0;
	}else{
		if (flag == 21){
			var xhr = new XMLHttpRequest();
			var type = "masque";
			var source = "masque2.png";
			var x2 = x+10;
			var y2 = y+10;
			xhr.open("GET", "Outils?x="+x1+"&y="+y1+"&x2="+x2+"&y2="+y2+"&type="+type+"&fichier="+fichier+"&source="+source, true);
			xhr.send(null);
			flag = 0;
		}
		if (flag == 20){
			x1 = x+10;
			y1 = y+10;
			flag = 21;
		}
		if (flag == 22){
			var xhr = new XMLHttpRequest();
			var type = "rm";
			xhr.open("GET", "Outils?x="+x+"&y="+y+"&type="+type+"&fichier="+fichier, true);
			xhr.send(null);
			flag = 0;
		}
	}
}



