import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Offcanvas from 'react-bootstrap/Offcanvas';
import { Navbar, Container } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import "./Sidebar.scss"


const Sidebar = () => {
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <>
            <Navbar expand="lg" className='nav_custom'>
                <Container>
                    <Navbar.Brand href="#">neonTokyoComics</Navbar.Brand>
                    <Button className="sidebar-toggles bg-white" variant="outline-secondary" onClick={handleShow}>
                        <FontAwesomeIcon icon={faBars} style={{color: "#00000",}}  />
                    </Button>
                </Container>
            </Navbar>



            <Offcanvas show={show} onHide={handleClose}>
                <Offcanvas.Header closeButton>
                    <Offcanvas.Title>Menu</Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>
                    <ul className="nav flex-column">
                        <li className="nav-item">
                            <Link to="/" className="nav-link active" onClick={handleClose} > <Button> Home</Button></Link>
                        </li>
                        <li className="nav-item">
                            <Link to="books" className="nav-link"  onClick={handleClose}> <Button> Libri </Button> </Link>
                        </li>
                        <li className="nav-item">
                            <Link to="author" className="nav-link"  onClick={handleClose} ><Button>Autori</Button></Link>
                        </li>
                    </ul>
                </Offcanvas.Body>
            </Offcanvas>
        </>
    );
}

export default Sidebar;

