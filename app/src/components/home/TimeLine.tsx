import * as React from 'react';
import {ISharedItem} from  '../../actions/home/timeline'

interface IProps {
sharedItems? : ISharedItem[],
addSharedItem(sharedItem: ISharedItem): void
}

interface IContext {
sharedItems?: ISharedItem[];
};


export class TimeLine extends React.Component<IProps,IContext> {

  public componentWillMount() {
    const evtSource = new EventSource('http://127.0.0.1:8080/timeline/all/');
    evtSource.onmessage = (e) => {
      this.props.addSharedItem(JSON.parse(e.data))
    }
  }

  render() {
    return (
      <div>
          {this.props.sharedItems.map(sharedItem => (
                <li key={sharedItem.id} >{sharedItem.title}</li>  
            ))
          }  
      </div>
    );
  }
}
