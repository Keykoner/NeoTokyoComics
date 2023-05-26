import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Layout from "./components/Layout/Layout";
import Home from "./components/Home/Home";
import Books from "./components/Books/Books";
import EditBooks from "./components/BookForm/EditBooks";
import NewBooks from "./components/BookForm/NewBooks";
import Author from "./components/Author/Author";
import NewAuthor from "./components/AuthorForm/NewAuthor";
import EditAuthor from "./components/AuthorForm/EditAuthor";




function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="books" element={<Books/>}>
            <Route path="edit/:id" element={<EditBooks />} /> 
            <Route path="new" element={<NewBooks/>} />
          </Route>
          <Route path="author" element={<Author/>} />
          <Route path="author/new" element={<NewAuthor/>}/>
          <Route path="author/edit/:id" element={<EditAuthor/>}/>
        </Route>
      </Routes>
    </Router>
  );
}

export default App;