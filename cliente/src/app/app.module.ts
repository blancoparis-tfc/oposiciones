import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ApplicationRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { MdCardModule } from '@angular2-material/card';
import { MdButtonModule } from '@angular2-material/button';
import { MdListModule } from '@angular2-material/list';
import { MdRadioModule } from '@angular2-material/radio';
import { MdProgressBarModule } from '@angular2-material/progress-bar';
import { MdIconModule } from '@angular2-material/icon';
import { MdToolbarModule } from '@angular2-material/toolbar';
import { MdCoreModule } from '@angular2-material/core';
import { HttpModule, JsonpModule } from '@angular/http';
import { routing, appRoutingProviders} from './app.routes'



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpModule,
    JsonpModule,
    routing,
    MdListModule,
    MdIconModule,
    MdRadioModule,
    MdProgressBarModule,
    MdCardModule,
    MdButtonModule,
    MdCoreModule,
   FormsModule
  ],
  providers: [
    appRoutingProviders
  ],
  entryComponents: [AppComponent],
  bootstrap: [AppComponent]
})
export class AppModule {

}
