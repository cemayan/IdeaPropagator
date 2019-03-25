import { connect } from 'react-redux';
import {login, ILoginForm}  from '../../actions/auth/index'
import {LoginForm} from '../../components/login/LoginForm';

export const mapDispatchToProps = (dispatch) => {
  return {
    login : (loginform : ILoginForm) => {

      fetch('http://localhost:8080/users/signin',{
        method: 'POST',
        headers : {
          "Content-Type" : "application/json"
        },
        body : JSON.stringify(loginform)
      }).then((response) => {
          if(response.status == 200) {
            return response.text();
          }
          else {
             throw "Başarısız giriş";
          }
      }).then((token) => {
          loginform.token = token;
          sessionStorage.setItem("token", token);
          dispatch(login(loginform))
          window.location.reload()
      }).catch((e) => console.log(e) )
    }
  }
}

export const mapStateToProps = (loginForm: ILoginForm) => {
  return {
    states : loginForm
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);

