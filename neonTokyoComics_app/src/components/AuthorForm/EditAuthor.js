import { useParams } from "react-router-dom";
import AuthorForm from "./AuthorForm";
import { useGet } from "../_Hooks/Customs";





const EditAuthor = () => {

    const {id} = useParams();

    const {data, error} = useGet("http://localhost:2424/author" , id)

  return (

    <div className="container">
        <h5>Modifica Authora</h5>
        <AuthorForm data={data}/>
    </div>
  )
}

export default EditAuthor;