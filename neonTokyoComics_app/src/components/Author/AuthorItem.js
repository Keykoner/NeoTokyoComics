import { Link } from "react-router-dom";
import { useDelete, useGet } from "../_Hooks/Customs";
import Alert from 'react-bootstrap/Alert';
import { useState } from "react";



const AuthorItem = ({ author, deleteSuccess }) => { //Definisce una funzione di tipo freccia che rappresenta il componente

    const [showDelete, setShowDelete] = useState(false);

    const { data: books, error } = useGet("http://localhost:2424/author/" + author.id + "/books")


    const deleteData = useDelete("http://localhost:2424/author", author.id )

    const performDelete = () => {
        deleteData(deleteSuccess);
     };

    return (
        <article className="col-12">
            <div className="m-2 p-2 border">
                <div className="row">
                    <div className="col-12">
                        {author.name}
                    </div>
                    <div className="col-6">
                        {author.alias ? author.alias : " "}
                    </div>
                    <div className="col-6">
                        {author.birthDate ? author.birthDate.substring(0, 10) : "Data non definita"}
                    </div>
                    <div className="col-12">
                        <div className="d-flex justify-content-around">
                            <Link className="btn btn-outline-info" to={"edit/" + author.id}>
                                Modifica
                            </Link>
                            <button className="btn btn-outline-danger" onClick={()=>{setShowDelete(true)}}>
                                Elimina
                            </button>
                        </div>
                    </div>
                    <div className="col-12">
                        <Alert className="mt-2" show={showDelete} variant="danger">
                            <Alert.Heading>Eliminare {author.name} ? </Alert.Heading>
                            {books && books.length > 0 ? 
                            (
                                <p>
                                Verranno eliminati anche: {books.length} libri/o . Vuoi Procedere ?
                            </p>

                            )
                            : "" }
                            
                            
                            <div className="d-flex justify-content-end">
                                <button className="btn btn-outline-success btn-sm me-2" onClick={performDelete}>
                                    Conferma
                                </button>
                                <button  className="btn btn-outline-danger btn-sm me-2" onClick={() => setShowDelete(false)}>
                                    Annulla
                                </button>
                            </div>
                        </Alert>

                    </div>

                </div>
            </div>

        </article>

    );
}

export default AuthorItem;