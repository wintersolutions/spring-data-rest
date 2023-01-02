// Generated by delombok at Tue Aug 11 12:51:25 CEST 2020
/*
 * Copyright 2014-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.rest.webmvc.json.patch;

import org.springframework.data.rest.webmvc.json.patch.SpelPath.UntypedSpelPath;

/**
 * Operation that replaces the value at the given path with a new value.
 *
 * @author Craig Walls
 * @author Oliver Gierke
 */
class ReplaceOperation extends PatchOperation {
	/**
	 * Constructs the replace operation
	 *
	 * @param path The path whose value is to be replaced. (e.g., '/foo/bar/4')
	 * @param value The value that will replace the current path value.
	 */
	private ReplaceOperation(UntypedSpelPath path, Object value) {
		super("replace", path, value);
	}

	public static ReplaceOperationBuilder valueAt(String path) {
		return new ReplaceOperationBuilder(path);
	}

	static class ReplaceOperationBuilder {
		private final String path;

		public ReplaceOperation with(Object value) {
			return new ReplaceOperation(SpelPath.untyped(path), value);
		}

		@java.lang.SuppressWarnings("all")
		private ReplaceOperationBuilder(final String path) {
			this.path = path;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.rest.webmvc.json.patch.PatchOperation#perform(java.lang.Object, java.lang.Class)
	 */
	@Override
	void perform(Object target, Class<?> type, BindContext context) {
		path.bindForWrite(type, context).setValue(target, evaluateValueFromTarget(target, type, context));
	}
}
