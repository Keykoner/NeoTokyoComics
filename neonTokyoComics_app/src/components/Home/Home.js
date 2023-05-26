import './Home.scss';


import CardComponent from '../Card/CardComponents';
import CardAuthor from '../cardAuthor/CardAuthor';

const Home = () => {
    return (
        <>
      
        <h2 className='center'>Home Page</h2>
 <h3 className='center'> ecco la nostra lista di libri</h3>
 
 <div className='element'>
     <CardComponent className='container center'/>

 </div>
 <h3 className='center'> ecco la nostra lista di autori</h3>
 
 <div className='element'>
     <CardAuthor className='container center'/>

 </div>

 <footer>
    <h1>ciao</h1>
    <h1>ciao</h1>
    <h1>ciao</h1>
    <h1>ciao</h1>
  
 </footer>


 
   
      </>

    );
}

export default Home;