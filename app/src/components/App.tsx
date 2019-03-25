import * as React from 'react';
import {  Container,Image,Menu } from 'semantic-ui-react'
import './App.css';
import {BrowserRouter as Router,Route,Link} from 'react-router-dom';
import TimeLine  from '../containers/home/TimeLine';
import  LoginForm from '../containers/auth/LoginForm';


class App extends React.Component<any,any> {

  public checkToken () {
    //TODO:Check token eklenecek
  }

  public render() {
    return (
      <div>
        {sessionStorage.getItem("token")=== null ?
        <LoginForm />
        :
        <Router>
          <div>
            <Menu fixed='top' inverted>
              <Container>
                <Menu.Item as='a'  header>
                  <Image size='mini' src='favicon.ico' style={{ marginRight: '1.5em' }} />
                  Idea Propagator
                </Menu.Item>
                  <Link exact="true"  to='/' className="item">Home</Link>
              </Container>
            </Menu>
        
            <Container text style={{ marginTop: '7em' }}>
                    <Route exact path="/" render={(props) =>(<TimeLine {...props}  />)} />
            </Container>
            </div>
        </Router>
        }   
    </div>
    );
  }
}

export default App;
