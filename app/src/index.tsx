import * as React from 'react';
import * as ReactDOM from 'react-dom';
import App  from './components/App';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import {Provider} from 'react-redux' 
import reducer from  './reducers/index';
import configureStore, { history } from './configureStore';
import { ConnectedRouter } from 'connected-react-router'

const store = configureStore();


ReactDOM.render(
  <Provider store={store}>
      <ConnectedRouter history={history}>  
          <App/>
      </ConnectedRouter>
  </Provider>,
  document.getElementById('root') as HTMLElement
);
registerServiceWorker();
