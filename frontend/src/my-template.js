import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';

class MyTemplate extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;">
 <vaadin-horizontal-layout class="header" style="width: 100%; min-height: var(--lumo-size-l); background-color: var(--lumo-contrast-10pct)"></vaadin-horizontal-layout>
 <vaadin-vertical-layout class="content" style="width: 100%; min-height: 0; flex: 1;"></vaadin-vertical-layout>
 <vaadin-horizontal-layout class="footer" style="width: 100%; min-height: var(--lumo-size-l); background-color: var(--lumo-contrast-10pct)"></vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'my-template';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(MyTemplate.is, MyTemplate);
