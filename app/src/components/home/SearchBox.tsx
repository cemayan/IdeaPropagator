import * as React from 'react';
import { Input } from 'semantic-ui-react';


interface IProps {

}

interface IContext {
};


export class SearchBox extends React.Component<IProps,IContext> {

  public componentDidMount() {
    // var socket = new WebSocket('ws://localhost:8083/wc/timeline');
    // // <2>
    // socket.addEventListener('message', function (event) {
    //   console.log('message from server: ' + event.data);
    // });
   
  }


  public onKeyPress(event) {
    if(event.key === "Enter") {
      //TODO :  Addd shared item  eklenecek.
    }

  }

  render() {
    return (
      <div>
         <Input loading icon='user' placeholder='Type something...' onKeyPress={this.onKeyPress} fluid />
      </div>
    );
  }
}
