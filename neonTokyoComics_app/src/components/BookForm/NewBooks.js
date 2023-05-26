import BookForm from "./BookForm";
import { useOutletContext } from "react-router-dom";
import "./NewBoks.scss"

const NewBooks = () => {


    const { mutate } = useOutletContext(); // useOutletContext permette di reperire le proprietà e o funzioni passate al context dall'outlet (vedi Books.js)

    return (
        <>
            <div className="m-2 p-2 border">
      
                    <h5>Nuovo Libro</h5>
                <BookForm mutate={mutate} />
   

            </div>

        </>
    )
}

export default NewBooks;

