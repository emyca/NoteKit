package com.example.notekit.pin_pad.presentation

import androidx.lifecycle.ViewModel
import com.example.notekit.core.utils.CryptoManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal sealed interface PinPadEvent {
    data class AddDigit(val digit: String) : PinPadEvent
    data object DeleteDigit : PinPadEvent
    data object ClearPin : PinPadEvent
    data object CheckPin : PinPadEvent
}

sealed interface PinPadState {
    data class PinState(
        val isAuthenticated: Boolean = false,
        val pinInput: String = "",
        val pinInfo: PinInfo? = null,
        val pinConfirm: String? = null
    ) : PinPadState
}

@HiltViewModel
internal class PinPadViewModel @Inject constructor(
    private val cryptoManager: CryptoManager
) : ViewModel() {

    private val _state = MutableStateFlow<PinPadState>(
        PinPadState.PinState()
    )

    val state = _state.asStateFlow()

    init {
        val pinSet = cryptoManager.getPin().isNullOrBlank()
        _state.update {
            it.copy(
                isAuthenticated = !pinSet
            )
        }
    }

    fun onEvent(event: PinPadEvent) {
        when (event) {
            is PinPadEvent.DeleteDigit -> {
                _state.update {
                    it.copy(
                        inputPin = it.inputPin.substring(0, it.inputPin.length - 1)
                    )
                }
            }

            is PinPadEvent.AddDigit -> {
                _state.update {
                    it.copy(
                        inputPin = it.inputPin.plus(event.digit)
                    )
                }
            }

            is PinPadEvent.ClearPin -> {
                _state.update {
                    it.copy(
                        inputPin = ""
                    )
                }
            }

            is PinPadEvent.CheckPin -> {
                val currentState = _state.value
                if (currentState.inputPin.length == 4) {
                    if (currentState.isAuthenticated) {
                        val storedPin = cryptoManager.getPin()
                        if (currentState.inputPin == storedPin) {
                            _state.update {
                                it.copy(
                                    error = PinInfo.SUCCESS
                                )
                            }
                        } else {
                            _state.update {
                                it.copy(
                                    error = PinInfo.INCORRECT_PASS
                                )
                            }

                            onEvent(PinPadEvent.ClearPin)
                        }
                    } else {
                        if (currentState.confirmPin.isNullOrBlank()) {
                            _state.update {
                                it.copy(
                                    confirmPin = currentState.inputPin,
                                    error = PinInfo.TRY_PIN_AGAIN
                                )
                            }
                            onEvent(PinPadEvent.ClearPin)
                        } else {
                            if (currentState.inputPin == currentState.confirmPin) {
                                cryptoManager.savePin(currentState.inputPin)
                                _state.value = currentState.copy(
                                    isAuthenticated = true,
                                    error = PinInfo.SUCCESS,
                                    confirmPin = null
                                )
                            } else {
                                _state.value = currentState.copy(
                                    error = PinInfo.INCORRECT_PASS,
                                    confirmPin = null
                                )
                                onEvent(PinPadEvent.ClearPin)
                            }
                        }
                    }
                } else {
                    _state.value = currentState.copy(error = PinInfo.NOT_ENOUGH_DIGITS)
                    onEvent(PinPadEvent.ClearPin)
                }
            }
        }
    }
}

enum class PinInfo(val info: Int) {
    SUCCESS(0),
    INCORRECT_PASS(1),
    NOT_ENOUGH_DIGITS(2),
    TRY_PIN_AGAIN(3)
}