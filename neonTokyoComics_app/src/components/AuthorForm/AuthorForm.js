import { useEffect, useState } from "react"; //sono hook di React utilizzati per gestire l'effetto collaterale e lo stato all'interno del componente.
import { FloatingLabel } from "react-bootstrap"; //è un componente di React Bootstrap che crea un'etichetta fluttuante per i campi di input.
import { Link, useNavigate } from "react-router-dom"; //sono componenti e hook di React Router utilizzati per la navigazione all'interno dell'applicazione.
import { usePut, usePost } from "../_Hooks/Customs"; //sono hook personalizzati che vengono importati dal file "../_Hooks/Customs" e vengono utilizzati per effettuare richieste PUT e POST verso un server.
import Alert from "../Alert/Alert";//è un componente personalizzato che mostra un messaggio di avviso.



const AuthorForm = ({data = {}}) => { //una funzione che prende un oggetto data come argomento. L'oggetto data rappresenta i dati di un'autore.

    const [author, setAuthor] = useState({//è un oggetto di stato che contiene le informazioni dell'autore, inizializzato con valori vuoti per name, alias e birthDate.
            name: "",
            alias: "",
            birthDate: ""
    });

    const [alertShow, setAlertShow] = useState(false);   //è una variabile di stato che indica se l'alert deve essere mostrato o nascosto.

    const [alertMessage, setAlertMessage] = useState(""); //è una variabile di stato che contiene il messaggio da mostrare nell'alert.

    const putData = usePut("http://localhost:2424/author", data.id); // Restituisce la funzione per il salvataggio dei dati

    const postData = usePost("http://localhost:2424/author"); // Restituisce la funzione per la creazione dei dati 
 
    const navigate = useNavigate();  //è una variabile che utilizza il hook useNavigate per ottenere una funzione di navigazione che può essere utilizzata per cambiare la route dell'applicazione.

    useEffect(() => { //è un hook di React che viene eseguito dopo che il componente è stato renderizzato e ogni volta che il valore dell'array di dipendenze [data] cambia. In questo caso, controlla se l'ID dell'autore nel parametro data è maggiore di 0. Se lo è, imposta lo stato author con i valori name, alias e birthDate tratti dall'oggetto data.
        if(data.id > 0) {
            setAuthor({
                name: data.name,
                alias: data.alias,
                birthDate: data.birthDate ? data.birthDate : ""
            })
        }
    }, [data])

    const handleChange = (e) => { //è una funzione che gestisce l'evento di cambio di valore negli input. Aggiorna lo stato author con il nuovo valore inserito dall'utente.
        setAuthor((prevValues) => {
            return {
                ...prevValues,
                [e.target.name]: e.target.value
            }
        });
    }

    const handleSubmit = (e) => { //è una funzione che gestisce l'invio del modulo.
         //Viene chiamata quando l'utente fa clic sul pulsante "Salva". Controlla se l'ID dell'autore
          //è maggiore di 0. Se lo è, chiama la funzione putData per salvare i dati dell'autore tramite
          // una richiesta PUT al server, altrimenti chiama la 
        //funzione postData per creare nuovi dati dell'autore tramite una richiesta POST al server.
        e.preventDefault();
        // Codice per il salvataggio
        if(data.id > 0) {
            // se l'id è maggiore di 0 siamo in "edit"
            putData(author, submitSucces);   // data -> ; successFn -> submitSuccess (vedi Customs.js / usePut)   
        }
        else {
            // se l'id è undefined o 0 siamo in "new"/post
            postData(author, submitSucces); 
        }
    }

    const alertDismiss = () => { //è una funzione che gestisce la chiusura dell'alert. Imposta lo stato 
        setAlertShow(false); //su false per nascondere l'alert e utilizza la funzione navigate per navigare alla route "/author" sostituendo la cronologia di navigazione corrente.
        navigate("/author", { replace: true });  // il replace è come se "interrompesse" la cronologia di navigazione
       
    }


    const submitSucces = () => { //è una funzione che viene chiamata quando il salvataggio dei dati è completato con successo. Imposta lo stato alertMessage con il messaggio "Salvataggio completato!" e imposta lo stato alertShow su true per mostrare l'alert.
        setAlertMessage("Salvataggio completato!");                
        setAlertShow(true);
    } 


    return (
        <>
        <form className="row">
            <div className="col-12">
                <FloatingLabel controlId="txtName" label="Nome" className="my-2">
                    <input id="txtName" className="form-control" name="name" value={author.name} onChange={handleChange} placeholder="Nome"></input>
                </FloatingLabel>
            </div>
            {/* <div className="col-12">
                <FloatingLabel controlId="txtAlias" label="Alias" className="my-2">
                    <input id="txtAlias" className="form-control" name="alias" value={author.alias} onChange={handleChange} placeholder="Cognome"></input>
                </FloatingLabel>
            </div> */}
            <div className="col-12">
                <FloatingLabel controlId="txtBirth" label="Compleanno" className="my-2">
                    <input id="txtBirth" className="form-control" type="date" name="birthDate" value={author.birthDate.substring(0,10)} onChange={handleChange} placeholder="Compleanno"></input>
                </FloatingLabel>
            </div>
            <div className="col-12">
                <div className="d-flex justify-content-around">
                    <button className="btn btn-sm btn-success" onClick={handleSubmit}>Salva</button>
                    <Link className="btn btn-sm btn-outline-danger" to="/author">Annulla</Link>
                </div>
            </div>
        </form>
        <Alert show={alertShow} onHide={alertDismiss} message={alertMessage}/>
        </>
    );
}

export default AuthorForm;
//Il componente AuthorForm restituisce il codice JSX per
// la rappresentazione del form dell'autore. I campi di input
// sono legati allo stato author per mostrare e aggiornare i valori.
// Quando l'utente fa clic sul pulsante "Salva", viene chiamata la funzione handleSubmit.
// L'alert viene mostrato o nascosto in base allo stato alertShow.
// L'alert personalizzato viene importato e utilizzato nella riga successiva al form.