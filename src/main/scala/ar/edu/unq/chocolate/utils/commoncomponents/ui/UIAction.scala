package ar.edu.unq.chocolate.utils.commoncomponents.ui;

trait UIAction {

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	def perform(component: UIComponent): Unit
}
