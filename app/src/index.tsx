import * as React from 'react';
import * as ReactDOM from 'react-dom';
import App  from './components/App';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import {createStore, applyMiddleware, combineReducers} from 'redux' 
import {Provider} from 'react-redux' 
import reducer from  './reducers/index';
import thunk from 'redux-thunk'; 

const store = createStore(reducer, applyMiddleware(thunk));

ReactDOM.render(
  <Provider store={store}>
  <App  {...store.getState().loginForm}  />
  </Provider>,
  document.getElementById('root') as HTMLElement
);
registerServiceWorker();
