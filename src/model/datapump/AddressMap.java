/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.datapump;

/**
 *
 * @author The Bright
 */
public class AddressMap{

        /**
         * @return the stateCode
         */
        public int getStateCode() {
            return stateCode;
        }

        /**
         * @param stateCode the stateCode to set
         */
        public void setStateCode(int stateCode) {
            this.stateCode = stateCode;
        }

        /**
         * @return the state
         */
        public String getState() {
            return state;
        }

        /**
         * @param state the state to set
         */
        public void setState(String state) {
            this.state = state;
        }

        /**
         * @return the lga
         */
        public String getLga() {
            return lga;
        }

        /**
         * @param lga the lga to set
         */
        public void setLga(String lga) {
            this.lga = lga;
        }

        /**
         * @return the lgaCode
         */
        public int getLgaCode() {
            return lgaCode;
        }

        /**
         * @param lgaCode the lgaCode to set
         */
        public void setLgaCode(int lgaCode) {
            this.lgaCode = lgaCode;
        }
        private String state;
        private String lga;
        private int lgaCode;
        private int stateCode;
    }
