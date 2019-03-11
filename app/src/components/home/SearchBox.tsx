import * as React from 'react';
import { Input } from 'semantic-ui-react';


interface IProps {

}

interface IContext {
};


export class SearchBox extends React.Component<IProps,IContext> {
  render() {
    return (
      <div>
         <Input loading icon='user' placeholder='Type something...' fluid />
      </div>
    );
  }
}
