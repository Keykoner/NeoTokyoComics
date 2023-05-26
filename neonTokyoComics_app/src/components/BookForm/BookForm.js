import { Link, useNavigate} from "react-router-dom";
import { useEffect, useState } from "react";
import { usePost, usePut } from "../_Hooks/Customs";
import FetchSelect from "../FetchSelect/FetchSelect";
import Alert from "../Alert/Alert";

const BookForm = ({data = {}, mutate}) => {

    const [books, setBooks] = useState({
        name: "",
        quantity: 0,
        price: 0.0,
        publishDate: "",
        idAuthor: 0,
        idType: 0,
        publishing_hause: "",
    })

    const [alertShow, setAlertShow] = useState(false); // Variabile di stato per gestire la visualizzazione dell'alert
    const [alertMessage, setAlertMessage] = useState(""); //  // Variabile di stato per gestire il messaggio dell'alert

    const putData = usePut("http://localhost:2424/books",data.id); // usePut restituiesce la funzione per il salvataggio dei dati

    const postData = usePost("http://localhost:2424/books"); // // usePost restituiesce la funzione per la cereazione dato dei dati

    const navigate = useNavigate();

    useEffect(() => {
        if(data.id > 0){
    setBooks({
        name: data.name,
        quantity: data.quantity,
        price: data.price,
        publishDate: data.publishDate ? data.publishDate : "",
        idAuthor: data.idAuthor,
        idType: data.idType,
        publishing_hause: data.publishing_hause, 
    });
}
}, [data.id, data.name, data.quantity, data.price, data.publishDate, data.idAuthor, data.idType, /*data.publishing_hause*/])

    const handleChange = (e) => {
        setBooks((prevValues) => {
            return {
                ...prevValues,
                [e.target.name] : e.target.value
            }
        });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
            // Codice per Salvataggio
        if(data.id > 0) {
            putData(books, submitSuccess); // data -> books; successFn -> submitSuccess ( vedi Customs.js / usePut)
        } 
        else   {
       postData(books, submitSuccess);
        }
        
    }

    const submitSuccess = () => {
        setAlertMessage("Salvataggio completato")
        setAlertShow(true);
    }

    const alertDismiss = () => {
        setAlertShow(false);
        navigate("/books", {replace: true});
        mutate();
    }

    return (

        <>
           
                    <form className=" row" onSubmit={handleSubmit}>
                        <div className=" col-6">
                            <label className=" form-lable">Titolo</label>
                            <input className=" form-control form-control-sm" name="name" value={books.name} onChange={handleChange}/>
                        </div>
                        <div className=" col-6">
                            <label className=" form-lable">autore</label>
                            <FetchSelect  className=" form-control form-control-sm" name="idAuthor" value={books.idAuthor} onChange={handleChange} url={"http://localhost:2424/author"}/>
                        </div>
                        <div className=" col-4">
                            <label className=" form-lable">Data</label>
                            <input className=" form-control form-control-sm" type="date" name="publishDate" value={books.publishDate.substring(0,10)} onChange={handleChange}/>
                        </div>
                        <div className=" col-4">
                            <label className=" form-lable">Genere</label>
                            <FetchSelect  className=" form-control form-control-sm" name="idType" value={books.idType} onChange={handleChange} url={"http://localhost:2424/types"}/>
                        </div>
                        {/* <div className=" col-4">
                            <label className=" form-lable">casa editrice</label>
                            <input className=" form-control form-control-sm"  name="punlishing_house" value={books.publishing_hause} onChange={handleChange}/>
                        </div>  */}
                        <div className=" col-2">
                            <label className=" form-lable">quantità</label>
                            <input className=" form-control form-control-sm" type="number"  min ={0} name="quantity" value={books.quantity} onChange={handleChange} />
                        </div>
                        <div className=" col-2">
                            <label className=" form-lable">Prezzo</label>
                            <input className=" form-control form-control-sm" type="number"step={0.01} min ={0} name="price" value={books.price} onChange={handleChange} />
                        </div>
                        <div className="col-12">
                            <div className=" d-flex justify-content-around mt-3">
                            <button className=" btn btn-success " type="submit">Salva</button>
                            <Link className=" btn btn-outline-danger " to="/books">Annulla</Link>
                            </div>
                        </div>
                    </form>
                    <Alert show={alertShow} onHide={alertDismiss} message ={alertMessage} />
                
            
        </>

    );

}

export default BookForm;