import * as React from 'react';
import {ISharedItem} from  '../../actions/home/timeline'
import { SearchBox } from './SearchBox';
import { Divider, Feed, Icon } from 'semantic-ui-react';

interface IProps {
states: any,
addSharedItem(sharedItem: ISharedItem): void,
fetchItems():any,
deleteSharedItem(id :string): void
}

interface IContext {
sharedItems?: ISharedItem[];
};


export class TimeLine extends React.Component<IProps,IContext> {

  public async componentDidMount() {
      this.props.fetchItems();
      var source = new EventSource('http://localhost:8080/sse/timeline')

      source.onmessage = (e) => {
        this.props.addSharedItem(JSON.parse(e.data).source)
      }


  }


  render() {
    console.log(this.props);
    return (
      <div>

         <SearchBox/>
         <Divider/>

         <Feed>
         {this.props.states.sharedItems.map(sharedItem => (
          <Feed.Event>
            <Feed.Label image='https://react.semantic-ui.com/images/avatar/small/joe.jpg' />
            <Feed.Content>
              <Feed.Summary>
                <a>Cem Ayan</a> posted on his page
                <Feed.Date>3 days ago</Feed.Date>
              </Feed.Summary>
              <Feed.Extra text>
                {sharedItem.content}
              </Feed.Extra>
              <Feed.Meta>
                <Feed.Like>
                  <Icon name='like' />
                  5 Likes
                </Feed.Like>
              </Feed.Meta>
            </Feed.Content>
          </Feed.Event>
       ))
      }  
      </Feed>
               {/* <a onClick={(e)=>this.props.deleteSharedItem(sharedItem.id)}>a</a> */}
      </div>
    );
  }
}
