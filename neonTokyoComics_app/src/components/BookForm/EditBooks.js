import BookForm from "./BookForm";
import { useOutletContext, useParams } from "react-router-dom";
import { useGet } from "../_Hooks/Customs";



const EditBooks = () => {

    const { id } = useParams();

    const { data, error } = useGet("http://localhost:2424/books", id);

    const { mutate } = useOutletContext(); // useOutletContext permette di reperire le proprietà e o funzioni passate al context dall'outlet (vedi books.js)


    if (data) {
        return (
            <>
                <div className="m-2 p-2 border">

                    <h5>Modifica Libro</h5>
                    <BookForm data={data} mutate={mutate} /> 
                </div>

            </>
        );
    }
}

export default EditBooks;

