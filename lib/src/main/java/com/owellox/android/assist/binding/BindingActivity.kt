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
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BindingActivity<Binding : ViewBinding> : AppCompatActivity() {
    private lateinit var _binding: Binding
    protected val binding: Binding
        get() = _binding

    /**
     * Override [onViewBound] instead to avoid potential view binding initializations.
     */
    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = onBindView()
        setContentView(binding.root)
        onViewBound(binding, savedInstanceState)
    }

    /**
     * Create an instance of [Binding] to be used in this activity.
     */
    abstract fun onBindView(): Binding

    /**
     * Called after [onBindView] has completed and this activity's content view has been set.
     */
    protected open fun onViewBound(binding: Binding, savedInstanceState: Bundle?) {}
}