/*
 * Copyright 2021 Owellox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.owellox.android.assist.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<Binding : ViewBinding> : Fragment() {
    private var _binding: Binding? = null

    protected val binding: Binding
        get() {
            return try {
                _binding!!
            } catch (e: NullPointerException) {
                throw IllegalStateException(
                    "Called before onViewBound() or after onDestroyView()"
                )
            }
        }

    /**
     * Override [onViewBound] instead to reduce boilerplate.
     */
    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = onBindView(inflater, container, savedInstanceState)
        return binding.root
    }

    /**
     * Override [onViewBound] instead.
     */
    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewBound(binding, savedInstanceState)
    }

    /**
     * Create an instance of [Binding] to be used in this activity.
     */
    abstract fun onBindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Binding

    /**
     * Called after [onBindView] has completed and this activity's content view has been set.
     */
    protected open fun onViewBound(binding: Binding, savedInstanceState: Bundle?) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}