
import Table from 'react-bootstrap/Table';
import BookRow from "./BookRow";
import { Outlet, Link } from "react-router-dom";
import { useGet } from "../_Hooks/Customs";
import { useState } from 'react';
import Alert from '../Alert/Alert';

const Books = () => {

    const { data, error, isLoading, mutate } = useGet("http://localhost:2424/books");

    const [alertShow, setAlertShow] = useState(false);
    const [AlertMessage, setAlertMessage] = useState("");

    const alertDismiss = () => {
        setAlertShow(false);
        mutate();
    }

    const deleteSuccess = ()=> {
        setAlertMessage("Libro eliminiato");
        setAlertShow(true);
    }




    if (data) {

        return (
            <div className="container">
                <Link className="btn btn-sm btn-success" to="new">nuovo libro </Link>
                <Outlet context={{ mutate }} /> {/* L'attributo context permette d ipassare proprietà e/o funzioni al componente che verrà renderizzato  */}
                <h4>Elenco dei libri</h4>
                <Table responsive>
                    <thead>
                        <tr>
                            <th></th>
                            <th>Titolo</th>
                            <th>Genere</th>
                            <th>prezzo</th>
                            <th>quantità</th>
                           
                        </tr>
                    </thead>
                    <tbody>
                        {data.map(books => (
                            <BookRow  key={books.id} books={books} deleteSuccess={deleteSuccess} />
                        ))}
                    </tbody>
                </Table>

                <Alert show={alertShow} onHide={alertDismiss } message={ AlertMessage} />

            </div>
        );
    }
}

export default Books;