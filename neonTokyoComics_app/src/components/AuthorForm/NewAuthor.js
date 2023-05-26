import AuthorForm from "./AuthorForm"
import "./NewAuthor.scss"



const NewAuthor = () => {

  return (
    <section className="container">
        <h5>Nuovo Autore</h5>
        <AuthorForm/>
        
    </section>
  )
}

export default NewAuthor;