/**
 *  Copyright (c) 2014 THALES GLOBAL SERVICES.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *  Contributors:
 *     Helleboid Matthieu - initial API and implementation
 *     Anass Radouani 	 - initial API and implementation
 */
package org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ChildCreationExtenderManager;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.lyo.oslc4j.adaptormodel.adaptorinterfacegenerator.AdaptorInterfaceGenerator;
import org.eclipse.lyo.oslc4j.adaptormodel.adaptorinterfacegenerator.AdaptorInterfaceGeneratorContainer;
import org.eclipse.lyo.oslc4j.adaptormodel.adaptorinterfacegenerator.AdaptorInterfaceGeneratorPackage;
import org.eclipse.lyo.oslc4j.adaptormodel.adaptorinterfacegenerator.ResourceMapping;
import org.eclipse.lyo.oslc4j.adaptormodel.adaptorinterfacegenerator.util.AdaptorInterfaceGeneratorSwitch;
import org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.OSLC4EMFgeneratorFactory;
import org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.OSLC4EMFgeneratorPackage;
import org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.util.OSLC4EMFgeneratorAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OSLC4EMFgeneratorItemProviderAdapterFactory extends OSLC4EMFgeneratorAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable, IChildCreationExtender {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This helps manage the child creation extenders.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChildCreationExtenderManager childCreationExtenderManager = new ChildCreationExtenderManager(OSLC4EMFGeneratorEditPlugin.INSTANCE, OSLC4EMFgeneratorPackage.eNS_URI);

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OSLC4EMFgeneratorItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.EStructuralFeatureMapping} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EStructuralFeatureMappingItemProvider eStructuralFeatureMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.EStructuralFeatureMapping}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEStructuralFeatureMappingAdapter() {
		if (eStructuralFeatureMappingItemProvider == null) {
			eStructuralFeatureMappingItemProvider = new EStructuralFeatureMappingItemProvider(this);
		}

		return eStructuralFeatureMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.EClassMapping} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClassMappingItemProvider eClassMappingItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.EClassMapping}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEClassMappingAdapter() {
		if (eClassMappingItemProvider == null) {
			eClassMappingItemProvider = new EClassMappingItemProvider(this);
		}

		return eClassMappingItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.OSLC4EMFCoreGenerator} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OSLC4EMFCoreGeneratorItemProvider oslc4EMFCoreGeneratorItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.OSLC4EMFCoreGenerator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOSLC4EMFCoreGeneratorAdapter() {
		if (oslc4EMFCoreGeneratorItemProvider == null) {
			oslc4EMFCoreGeneratorItemProvider = new OSLC4EMFCoreGeneratorItemProvider(this);
		}

		return oslc4EMFCoreGeneratorItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.OSLC4EMFExposerGenerator} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OSLC4EMFExposerGeneratorItemProvider oslc4EMFExposerGeneratorItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.OSLC4EMFExposerGenerator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOSLC4EMFExposerGeneratorAdapter() {
		if (oslc4EMFExposerGeneratorItemProvider == null) {
			oslc4EMFExposerGeneratorItemProvider = new OSLC4EMFExposerGeneratorItemProvider(this);
		}

		return oslc4EMFExposerGeneratorItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.PackagesExportContainer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackagesExportContainerItemProvider packagesExportContainerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.PackagesExportContainer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPackagesExportContainerAdapter() {
		if (packagesExportContainerItemProvider == null) {
			packagesExportContainerItemProvider = new PackagesExportContainerItemProvider(this);
		}

		return packagesExportContainerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.PackageExport} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageExportItemProvider packageExportItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.PackageExport}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPackageExportAdapter() {
		if (packageExportItemProvider == null) {
			packageExportItemProvider = new PackageExportItemProvider(this);
		}

		return packageExportItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.RequiredPluginsContainer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequiredPluginsContainerItemProvider requiredPluginsContainerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.RequiredPluginsContainer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRequiredPluginsContainerAdapter() {
		if (requiredPluginsContainerItemProvider == null) {
			requiredPluginsContainerItemProvider = new RequiredPluginsContainerItemProvider(this);
		}

		return requiredPluginsContainerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.RequiredPlugin} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequiredPluginItemProvider requiredPluginItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.RequiredPlugin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRequiredPluginAdapter() {
		if (requiredPluginItemProvider == null) {
			requiredPluginItemProvider = new RequiredPluginItemProvider(this);
		}

		return requiredPluginItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.RequiredPluginFeature} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequiredPluginFeatureItemProvider requiredPluginFeatureItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.RequiredPluginFeature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRequiredPluginFeatureAdapter() {
		if (requiredPluginFeatureItemProvider == null) {
			requiredPluginFeatureItemProvider = new RequiredPluginFeatureItemProvider(this);
		}

		return requiredPluginFeatureItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.PackageExportFeature} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageExportFeatureItemProvider packageExportFeatureItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.lyo.oslc4j.adaptormodel.emf.oslc4emfgenerator.PackageExportFeature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPackageExportFeatureAdapter() {
		if (packageExportFeatureItemProvider == null) {
			packageExportFeatureItemProvider = new PackageExportFeatureItemProvider(this);
		}

		return packageExportFeatureItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<IChildCreationExtender> getChildCreationExtenders() {
		return childCreationExtenderManager.getChildCreationExtenders();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
		return childCreationExtenderManager.getNewChildDescriptors(object, editingDomain);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return childCreationExtenderManager;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (eStructuralFeatureMappingItemProvider != null) eStructuralFeatureMappingItemProvider.dispose();
		if (eClassMappingItemProvider != null) eClassMappingItemProvider.dispose();
		if (oslc4EMFCoreGeneratorItemProvider != null) oslc4EMFCoreGeneratorItemProvider.dispose();
		if (oslc4EMFExposerGeneratorItemProvider != null) oslc4EMFExposerGeneratorItemProvider.dispose();
		if (packagesExportContainerItemProvider != null) packagesExportContainerItemProvider.dispose();
		if (packageExportItemProvider != null) packageExportItemProvider.dispose();
		if (requiredPluginsContainerItemProvider != null) requiredPluginsContainerItemProvider.dispose();
		if (requiredPluginItemProvider != null) requiredPluginItemProvider.dispose();
		if (requiredPluginFeatureItemProvider != null) requiredPluginFeatureItemProvider.dispose();
		if (packageExportFeatureItemProvider != null) packageExportFeatureItemProvider.dispose();
	}

	/**
	 * A child creation extender for the {@link AdaptorInterfaceGeneratorPackage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static class AdaptorInterfaceGeneratorChildCreationExtender implements IChildCreationExtender {
		/**
		 * The switch for creating child descriptors specific to each extended class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		protected static class CreationSwitch extends AdaptorInterfaceGeneratorSwitch<Object> {
			/**
			 * The child descriptors being populated.
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected List<Object> newChildDescriptors;

			/**
			 * The domain in which to create the children.
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected EditingDomain editingDomain;

			/**
			 * Creates the a switch for populating child descriptors in the given domain.
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			CreationSwitch(List<Object> newChildDescriptors, EditingDomain editingDomain) {
				this.newChildDescriptors = newChildDescriptors;
				this.editingDomain = editingDomain;
			}
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public Object caseAdaptorInterfaceGeneratorContainer(AdaptorInterfaceGeneratorContainer object) {
				newChildDescriptors.add
					(createChildParameter
						(AdaptorInterfaceGeneratorPackage.Literals.ADAPTOR_INTERFACE_GENERATOR_CONTAINER__GENERATOR,
						 OSLC4EMFgeneratorFactory.eINSTANCE.createOSLC4EMFCoreGenerator()));

				newChildDescriptors.add
					(createChildParameter
						(AdaptorInterfaceGeneratorPackage.Literals.ADAPTOR_INTERFACE_GENERATOR_CONTAINER__GENERATOR,
						 OSLC4EMFgeneratorFactory.eINSTANCE.createOSLC4EMFExposerGenerator()));

				return null;
			}
 
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public Object caseResourceMapping(ResourceMapping object) {
				newChildDescriptors.add
					(createChildParameter
						(AdaptorInterfaceGeneratorPackage.Literals.RESOURCE_MAPPING__RESOURCE_PROPERTY_MAPPINGS,
						 OSLC4EMFgeneratorFactory.eINSTANCE.createEStructuralFeatureMapping()));

				return null;
			}
 
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			@Override
			public Object caseAdaptorInterfaceGenerator(AdaptorInterfaceGenerator object) {
				newChildDescriptors.add
					(createChildParameter
						(AdaptorInterfaceGeneratorPackage.Literals.ADAPTOR_INTERFACE_GENERATOR__RESOURCE_MAPPINGS,
						 OSLC4EMFgeneratorFactory.eINSTANCE.createEClassMapping()));

				return null;
			}
 
			/**
			 * <!-- begin-user-doc -->
			 * <!-- end-user-doc -->
			 * @generated
			 */
			protected CommandParameter createChildParameter(Object feature, Object child) {
				return new CommandParameter(null, feature, child);
			}

		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Collection<Object> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
			ArrayList<Object> result = new ArrayList<Object>();
			new CreationSwitch(result, editingDomain).doSwitch((EObject)object);
			return result;
		}

		/**
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public ResourceLocator getResourceLocator() {
			return OSLC4EMFGeneratorEditPlugin.INSTANCE;
		}
	}

}
