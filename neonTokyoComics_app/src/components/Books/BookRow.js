
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPencil, faTrashCan } from "@fortawesome/free-solid-svg-icons";
import { useGet,useDelete } from "../_Hooks/Customs";



const BookRow = ({books, deleteSuccess})=> {

    // const {data: type, author, error: typeError, authorError} = useGet(URL_TYPES, URL_AUTHOR, books.idType, books.idAuthor)
    // const deleteData = useDelete (URL_BOOKS, books.id);
    //  const performDelete = () => {
    //     deleteData(deleteSuccess);
    //  }


    const {data: type, error: typeError} = useGet("http://localhost:2424/types", books.idType);

    const {data: author, error: authorError} = useGet("http://localhost:2424/author", books.idAuthor );

    const deleteData = useDelete("http://localhost:2424/books", books.id);

    const performDelete = () => {
       deleteData(deleteSuccess);
        
    }
  

    return (
        <tr>
            <td className=" align-middle">
                <Link className="btn text-info" to={"edit/" + books.id}>
                    <FontAwesomeIcon icon={faPencil}/>
                </Link>
                <button className="btn text-danger"onClick={performDelete}>
                    <FontAwesomeIcon icon={faTrashCan}/>
                </button>
            </td>
            <td>
               <div>{books.name}</div> 
               <div className=" small">{author ? author.name : ""}</div> 
            
            </td>
            <td className=" align-middle">{type ? type.name : books.idType}</td>
            <td className=" align-middle">{books.price}</td>
            <td className=" align-middle">{books.quantity}</td>
            <td className=" align-middle">{books.publishing_hause}</td>
            

        </tr>

    );
}


export default BookRow;