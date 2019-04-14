import * as React from 'react';
import {  Container,Image,Menu } from 'semantic-ui-react'
import './App.css';
import {BrowserRouter as Router,Route,Link, Redirect, Switch} from 'react-router-dom';
import TimeLine  from '../containers/home/TimeLine';
import  LoginForm from '../containers/auth/LoginForm';
import {history} from '../helpers/history';
import NoMatch from './NoMatch';


class App extends React.Component<any,any> {

  public checkToken () {
    //TODO:Check token eklenecek
  }

  public render() {
    return (

      <div>
            {sessionStorage.getItem("token") === null ?  
                 <Switch>
                    <Route exact  render={(props) =>(<LoginForm />)} />
                 </Switch>

            : 

            <div>
                  <Menu fixed='top' inverted>
                      <Container>
                      <Menu.Item as='a'  header>
                        <Image size='mini' src='favicon.ico' style={{ marginRight: '1.5em' }} />
                    Idea Propagator
                      </Menu.Item>
                        <Link exact  to='/home' className="item">Home</Link>
                      </Container>
                  </Menu>

                  <Container text style={{ marginTop: '7em' }}>
                        <Switch>
                        <Route exact path="/" render={(props) =>(<TimeLine {...props}  />)} />
                          <Route exact path="/home" render={(props) =>(<TimeLine {...props}  />)} />
        
                          <Route component={NoMatch} />
                        </Switch>  
                  </Container>    


                </div>
              }

        </div>

    );
  }
}





export default App;
