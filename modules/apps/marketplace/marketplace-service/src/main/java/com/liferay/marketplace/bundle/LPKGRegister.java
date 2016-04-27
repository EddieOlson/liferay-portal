/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.marketplace.bundle;

import com.liferay.lpkg.deployer.LPKGDeployer;
import com.liferay.marketplace.model.App;
import com.liferay.marketplace.service.AppLocalService;
import com.liferay.marketplace.service.ModuleLocalService;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.URL;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.osgi.framework.Bundle;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true)
public class LPKGRegister {

	@Activate
	public void activate() throws Exception {
		Map<Bundle, List<Bundle>> lpkgBundles =
			_lpkgDeployer.getDeployedLPKGBundles();

		for (Bundle bundle : lpkgBundles.keySet()) {
			registerLPKGBundle(bundle);
		}
	}

	public void registerLPKGBundle(Bundle lpkgBundle) throws Exception {
		URL url = lpkgBundle.getEntry("liferay-marketplace.properties");

		Properties properties = PropertiesUtil.load(
			url.openStream(), StringPool.ISO_8859_1);

		long remoteAppId = GetterUtil.getLong(
			properties.getProperty("remote-app-id"));
		String version = properties.getProperty("version");

		if ((remoteAppId <= 0) || Validator.isNull(version)) {
			return;
		}

		String title = properties.getProperty("title");
		String description = properties.getProperty("description");
		String category = properties.getProperty("category");
		String iconURL = properties.getProperty("icon-url");
		boolean required = GetterUtil.getBoolean(
			properties.getProperty("required"));

		App app = _appLocalService.updateApp(
			0, remoteAppId, title, description, category, iconURL, version,
			required, null);

		String[] bundleInfos = StringUtil.split(
			properties.getProperty("bundles"));

		for (String bundleInfo : bundleInfos) {
			String[] bundleInfoParts = StringUtil.split(
				bundleInfo, CharPool.POUND);

			String bundleSymbolicName = bundleInfoParts[0];
			String bundleVersion = bundleInfoParts[1];
			String contextName = bundleInfoParts[2];

			_moduleLocalService.addModule(
				0, app.getAppId(), bundleSymbolicName, bundleVersion,
				contextName);
		}

		String[] contextNames = StringUtil.split(
			properties.getProperty("context-names"));

		for (String contextName : contextNames) {
			_moduleLocalService.addModule(
				0, app.getAppId(), StringPool.BLANK, StringPool.BLANK,
				contextName);
		}

		_appLocalService.processMarketplaceProperties(properties);
	}

	@Reference
	private AppLocalService _appLocalService;

	@Reference
	private LPKGDeployer _lpkgDeployer;

	@Reference
	private ModuleLocalService _moduleLocalService;

}