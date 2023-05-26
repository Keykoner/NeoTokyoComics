
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Card from 'react-bootstrap/Card';
import "./CardComponent.scss";

const CardAuthor = () => {
    const [datas, setdatas] = useState([]);
  
    useEffect(() => {
      // Recupera i dati dal database quando il componente viene montato
      fetchData();
    }, []);

    
  
    const fetchData = async () => {
      try {
        // Effettua una richiesta HTTP per ottenere i dati delle card dal tuo server
        const response = await axios.get('http://localhost:2424/author');
  
        // Imposta lo stato delle datas con i dati ottenuti dalla risposta del server
        setdatas(response.data);
      } catch (error) {
        console.error('Errore durante il recupero dei dati:', error);
      }
    };

  
    return (
      <div className="allaing_container my-scroll_bar overflow-scroll col-lg-12">
        {datas.map((data) => (
          <div key={data.id} className="data">
            {/* <h3>{data.title}</h3>
            <p>{data.name}</p> */}
            <div className='cardPosition '>   
            <Card>
      <Card.Body >
        <Card.Title>{data.name}</Card.Title>
        <Card.Subtitle className="mb-2 text-muted">{data.idAuthor}</Card.Subtitle>
        <Card.Text>
          se vuoi inserire o cercare altri autori o libri clicca qui:
        </Card.Text>
        <Card.Link href="http://localhost:3000/author">Autori</Card.Link>
        <Card.Link href="http://localhost:3000/books">Libri</Card.Link>
      </Card.Body>
    </Card>
            </div>
          </div>

        ))}
      </div>
    );
  };
  export default CardAuthor;