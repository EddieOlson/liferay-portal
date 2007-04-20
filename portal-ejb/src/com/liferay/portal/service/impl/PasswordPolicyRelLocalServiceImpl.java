/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.service.impl;

import com.liferay.counter.model.Counter;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchPasswordPolicyRelException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.service.base.PasswordPolicyRelLocalServiceBaseImpl;
import com.liferay.portal.service.persistence.PasswordPolicyRelUtil;

/**
 * <a href="PasswordPolicyRelLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class PasswordPolicyRelLocalServiceImpl
	extends PasswordPolicyRelLocalServiceBaseImpl {

	public PasswordPolicyRel addPasswordPolicyRel(
			long passwordPolicyId, String className, String classPK)
		throws PortalException, SystemException {

		PasswordPolicyRel passwordPolicyRel =
			PasswordPolicyRelUtil.fetchByP_C_C(
				passwordPolicyId, className, classPK);

		if (passwordPolicyRel != null) {
			return null;
		}

		long passwordPolicyRelId = CounterLocalServiceUtil.increment(
			Counter.class.getName());

		passwordPolicyRel = PasswordPolicyRelUtil.create(passwordPolicyRelId);

		passwordPolicyRel.setPasswordPolicyId(passwordPolicyId);
		passwordPolicyRel.setClassName(className);
		passwordPolicyRel.setClassPK(classPK);

		PasswordPolicyRelUtil.update(passwordPolicyRel);

		return passwordPolicyRel;
	}

	public void addPasswordPolicyRel(
			long passwordPolicyId, String className, String[] classPKs)
		throws PortalException, SystemException {

		for (int i = 0; i < classPKs.length; i++) {
			addPasswordPolicyRel(passwordPolicyId, className, classPKs[i]);
		}
	}

	public void addPasswordPolicyRel(
			long passwordPolicyId, String className, long[] classPKs)
		throws PortalException, SystemException {

		for (int i = 0; i < classPKs.length; i++) {
			addPasswordPolicyRel(
				passwordPolicyId, className, String.valueOf(classPKs[i]));
		}
	}

	public void deletePasswordPolicyRel(
			long passwordPolicyId, String className, String classPK)
		throws PortalException, SystemException {

		try {
			PasswordPolicyRelUtil.removeByP_C_C(
				passwordPolicyId, className, classPK);
		}
		catch (NoSuchPasswordPolicyRelException nsppre) {
		}
	}

	public void deletePasswordPolicyRel(
			long passwordPolicyId, String className, String[] classPKs)
		throws PortalException, SystemException {

		for (int i = 0; i < classPKs.length; i++) {
			deletePasswordPolicyRel(passwordPolicyId, className, classPKs[i]);
		}
	}

	public void deletePasswordPolicyRel(
			long passwordPolicyId, String className, long[] classPKs)
		throws PortalException, SystemException {

		for (int i = 0; i < classPKs.length; i++) {
			deletePasswordPolicyRel(
				passwordPolicyId, className, String.valueOf(classPKs[i]));
		}
	}

	public PasswordPolicyRel getPasswordPolicyRel(
			String className, String classPK)
		throws PortalException, SystemException {

		return PasswordPolicyRelUtil.findByC_C(className, classPK);
	}

	public PasswordPolicyRel getPasswordPolicyRel(
			long passwordPolicyId, String className, String classPK)
		throws PortalException, SystemException {

		return PasswordPolicyRelUtil.findByP_C_C(
			passwordPolicyId, className, classPK);
	}

	public boolean hasPasswordPolicy(
			long passwordPolicyId, String className, String classPK)
		throws PortalException, SystemException {

		if (PasswordPolicyRelUtil.fetchByP_C_C(
				passwordPolicyId, className, classPK) != null) {

			return true;
		}
		else {
			return false;
		}
	}

}