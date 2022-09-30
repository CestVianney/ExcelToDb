let dossierAccesPdf = "";

const dataUrl = 'http://localhost:8080/api/xlsx/values';
const pathUrl = 'http://localhost:8080/api/xlsx/path';
const openPdfUrl = 'http://localhost:8080/api/xlsx?path=';
const closeAppUrl = 'http://localhost:8080/api/javaapp';
const importExcelUrl = 'http://localhost:8080/api/import';
const checkHealthUrl = 'http://localhost:8080/api/javaapp/health';

const buttonPath = document.getElementById('btn')
async function displayTable() {
    const response = await fetch(dataUrl).then(response => response.json());
    var dataRetrieved = Promise.resolve(response)
        .then(
            data => {
                console.log(data);
                document.getElementById("listeDonnees").innerHTML = null;
                var t = "";
                for (var i = 0; i < data.length; i++) {
                    let posOrNeg = "";
                    if (data[i].ttc < 0) {
                        posOrNeg = "negative";
                    } else {
                        posOrNeg = "positive";
                    }
                    var tr = "<tr>";
                    tr += '<td class="' + posOrNeg + '">' + data[i].id + "</td>";
                    tr += '<td class="' + posOrNeg + '">' + data[i].journal + "</td>";
                    tr += '<td class="' + posOrNeg + '">' + data[i].date + "</td>";
                    tr += '<td class="' + posOrNeg + '"> <button onclick=getPdf("' + data[i].refPiece + '","false")>' + data[i].refPiece + "</button></td>";
                    tr += '<td class="' + posOrNeg + '">' + data[i].libelle + "</td>";
                    tr += '<td class="' + posOrNeg + '">' + data[i].tva + "</td>";
                    tr += '<td class="' + posOrNeg + '">' + data[i].ht + "</td>";
                    tr += '<td class="' + posOrNeg + '">' + data[i].ttc + "</td>";
                    tr += '<td class="' + posOrNeg + '">' + '<label><input type=' + "checkbox" + ' /></label>' + "</td>";
                    tr += '</tr>';
                    t += tr;
                }
                document.getElementById("listeDonnees").innerHTML += t;
            }
        )

}

function getPdf(pdfValue, ischosenpdf) {
    if (dossierAccesPdf == "") {
        window.alert("Veuillez renseigner le dossier de destination");
    } else {
        fetch(openPdfUrl + pdfValue + "&isAppelManuel=" + ischosenpdf, {
            method: "POST"

        }).then(response => {
            console.log(response.status);
            if (response.status != 200) {
                window.alert("Le document " + pdfValue + " recherché dans le chemin " + dossierAccesPdf + " n'existe pas.");
            }
        });
    }
}

function getchosenpdf() {
    const fourchar = document.getElementById('openchosenpdf').value;
    getPdf(fourchar, "true");
    document.getElementById('openchosenpdf').value = '';
}

function getchosenpdfkeydown() {
    if (event.key === 'Enter') {
        getchosenpdf();
    }
}
function filtrer() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("fltr");
    filter = input.value.toUpperCase();
    table = document.getElementById("retriever");
    tr = table.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[4];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function findPath() {
    fetch(pathUrl).then((response) => response.text()).then((text) => {
        dossierAccesPdf = text;
        btn.textContent = 'Chemin actuel = ' + dossierAccesPdf;
    });
}

function exitApp() {
    fetch(closeAppUrl, {
        method: "POST"
    })
}

async function importExcel() {
    await fetch(importExcelUrl, {
        method: "POST"
    });
    displayTable();
}

window.onload = (event) => {
    fetch(checkHealthUrl).then(async (response) => {
        console.log(response.status);
        if(response.status == 200) {
            document.body.style.backgroundImage = await "url('https://picsum.photos/1920/1080')";
        }
    }).catch((err) => {
        document.body.style.backgroundImage = "";
    });
    window.setInterval(function() {
        console.log("prout");
        fetch(checkHealthUrl).then(async (response) => {
            console.log(response.status);
            if(response.status == 200) {
                document.body.style.backgroundImage = await "url('https://picsum.photos/1920/1080')";
            }
        }).catch((err) => {
            document.body.style.backgroundImage = "";
        });
    }, 5000);
    
}

function alertContact() {
    window.alert("Vianney Charpentier - cestvianney@proton.me - 2022");
}