import * as React from 'react';
import * as ReactDOM from 'react-dom';
import App from './components/App';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import {createStore, combineReducers} from 'redux' 
import {Provider, connect} from 'react-redux' 
import * as  reducer from  './reducers/index' 

const store = createStore(reducer.sharedItems);

ReactDOM.render(
  <Provider store={store}>
  <App />
  </Provider>,
  document.getElementById('root') as HTMLElement
);
registerServiceWorker();
