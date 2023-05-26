import { Link } from "react-router-dom";
import { useGet } from "../_Hooks/Customs";

import Alert from "../Alert/Alert";
import { useState } from "react";
import AuthorItem from "./AuthorItem";



const Author = () => {

    const { data, error, mutate } = useGet("http://localhost:2424/author");

    const [alertShow, setAlertShow] = useState(false);
    const [AlertMessage, setAlertMessage] = useState("");

    const alertDismiss = () => {
        setAlertShow(false);
        mutate();
    }

    const deleteSuccess = ()=> {
        setAlertMessage("Eliminazione avvenuta");
        setAlertShow(true);
    }


    if (data) {
        return (

            <>
                <div className="container">
                    <h5> autori</h5>
                    <Link to="new" className="btn btn-success btn-sm">Nuovo autore</Link>
                    <div className="row">
                        {data.map(author => (
                            <AuthorItem  key={author.id} author={author}  deleteSuccess={deleteSuccess}/>
                        ))}
                    </div>
                </div>
                <Alert show={alertShow} onHide={alertDismiss} message={AlertMessage}/>
            </>


        );
    }
}

export default Author;